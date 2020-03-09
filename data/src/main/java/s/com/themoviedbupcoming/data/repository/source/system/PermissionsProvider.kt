package s.com.themoviedbupcoming.data.repository.source.system

import io.reactivex.Observable
import io.reactivex.Single

interface PermissionsProvider {

    fun isGranted(vararg permissions: String): Single<Boolean>
    fun requestPermissions(vararg permissions: String): Observable<Boolean>
}