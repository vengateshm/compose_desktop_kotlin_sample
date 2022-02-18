// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.network.MetalPricesApiClient
import repository.MetalPricesRepositoryImpl
import ui.MetalPricesScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val repository = remember { MetalPricesRepositoryImpl(MetalPricesApiClient.API) }
        MetalPricesScreen(repository)
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Metal Prices",
        icon = painterResource("images/ic_metal_prices.png"),
        resizable = true,
    ) {
        App()
    }
}
