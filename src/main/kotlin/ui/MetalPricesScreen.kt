package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.Result
import models.MetalPrice
import repository.MetalPricesRepository

@Composable
fun MetalPricesScreen(metalPricesRepository: MetalPricesRepository) {
    val result = remember { mutableStateOf<Result<MetalPrice>?>(null) }

    LaunchedEffect(true) {
        result.value = Result.Loading
        result.value = metalPricesRepository.getMetalPrice()
    }

    when (result.value) {
        is Result.Loading -> LoadingUI()
        is Result.Error -> ErrorUI()
        is Result.Success -> MetalPriceUI((result.value as Result.Success<MetalPrice>).data)
    }
}

@Composable
fun MetalPriceUI(metalPrice: MetalPrice) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Latest price of gold and silver in US dollars per ounce",
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(Modifier.height(16.dp))
        PriceUI("images/gold.png", "Gold", metalPrice.gold, Color(0XFFb8860b))
        Spacer(Modifier.height(4.dp))
        PriceUI("images/silver.png", "Silver", metalPrice.silver, Color(0XFF757575))
    }
}

@Composable
fun PriceUI(imagePath: String, metalName: String, metalPrice: Double, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(imagePath),
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "$metalName : $metalPrice",
            color = color,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ErrorUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Something went wrong. Please check your internet connection and try again!",
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 72.dp, vertical = 72.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error,
        )
    }
}

@Composable
fun LoadingUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .defaultMinSize(minWidth = 60.dp, minHeight = 60.dp)
        )
    }
}