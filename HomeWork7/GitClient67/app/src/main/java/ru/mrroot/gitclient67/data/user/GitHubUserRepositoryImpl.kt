package ru.mrroot.gitclient67.data.user


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import ru.mrroot.gitclient67.data.user.datasource.CacheUserDataSource
import ru.mrroot.gitclient67.data.user.datasource.UserDataSource
import javax.inject.Inject

class GitHubUserRepositoryImpl @Inject constructor(
    private val cloud: UserDataSource,
    private val cache: CacheUserDataSource
) : GitHubUserRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.merge(
            cache.getUsers().toObservable(),
            cloud.getUsers().flatMap(cache::retain).toObservable()
        )

    override fun getUserByLogin(userId: String): Maybe<GitHubUser> =
        cache.getUserByLogin(userId)
            .switchIfEmpty(cloud.getUserByLogin(userId))
            .onErrorResumeWith(
                cloud.getUserByLogin(userId)
            )

    override fun getUserRepositories(reposUrl: String): Observable<List<GitHubUserRepos>> =
        Observable.merge(
            cache.getUserRepos(reposUrl).toObservable(),
            cloud.getUserRepos(reposUrl).flatMap {
                cache.retainRepos(it, reposUrl)
            }.toObservable()
        )
}