package rs.droidtech.githubapi.repository.ui.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterContractImpl<Req, Res>(
    var viewContract: BaseViewContract<Res>?,
    var repository: DataRepositoryContract?,
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main,
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BasePresenterContract<Req>,
    CoroutineScope,
    DefaultLifecycleObserver {

    private val coroutineJob = Job()

    override val coroutineContext: CoroutineContext
        get() = uiDispatcher + coroutineJob

    override fun onDestroy(owner: LifecycleOwner) {
        cleanup()
        super.onDestroy(owner)
    }

    override fun cleanup() {
        // Cancel all coroutines running in this context
        coroutineContext.cancel()

        // Nullify view and repo in order to prevent memory leak
        viewContract = null
        repository = null

        // Cancel job
        coroutineJob.cancel()
    }
}