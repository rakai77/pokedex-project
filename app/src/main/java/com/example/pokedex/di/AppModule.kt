import com.example.pokedex.presentation.screen.detail.DetailViewModel
import com.example.pokedex.presentation.screen.home.HomeViewModel
import com.example.pokedex.presentation.screen.login.LoginViewModel
import com.example.pokedex.presentation.screen.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}