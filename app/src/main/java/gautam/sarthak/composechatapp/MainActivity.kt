package gautam.sarthak.composechatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import gautam.sarthak.composechatapp.ui.theme.ComposeChatAppTheme


@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    ) {
                        val viewModel: MainViewModel = hiltViewModel()
                        val dog = viewModel.state.value.dog
                        val isLoading = viewModel.state.value.isLoading
                        dog?.let {
                            Image(
                                painter = rememberImagePainter(
                                    data = dog.imageUrl,
                                    builder = { crossfade(true) }
                                ),
                                contentDescription = "Dog"
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = dog.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = dog.description)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Button(
                            onClick = viewModel::getRandomDogs,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Next dog!")
                        }
                        Spacer(Modifier.height(8.dp))
                        if(isLoading) {
                            CircularProgressIndicator(
                                progress = 0.89f,
                            )
                        }
                    }
                }
            }
        }
    }
}
