package rs.droidtech.githubapi.repository.ui.commits

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import rs.droidtech.githubapi.repository.repository.DataRepository
import rs.droidtech.githubapi.repository.repository.DataRepositoryContract
import rs.droidtech.githubapi.repository.repository.remote.RemoteRepository

class CommitsPresenterTest {

    private lateinit var view: CommitsView
    private lateinit var dataRepository: DataRepositoryContract
    private lateinit var presenter: CommitsPresenter
    private lateinit var remoteRepository: RemoteRepository

    @Before
    fun setup() {
        view = mock()
        remoteRepository = RemoteRepository()
        dataRepository = DataRepository(remoteRepository)

        presenter =
            CommitsPresenter(view, dataRepository)
    }

    @Test
    fun getData_calls_with_nullable_owner_or_repository_or_wrong_request_at_all() {
        presenter.getData(mapOf())
        verify(view).showInvalidDataError()
    }

    @Test
    fun getData_calls_with_nullable_owner_or_repository_or_wrong_request_at_all_never_calls_setData() {
        presenter.getData(mapOf("owner" to "octocat", "repo" to "github"))
        verify(view, never()).setData(com.nhaarman.mockitokotlin2.any())
    }
}