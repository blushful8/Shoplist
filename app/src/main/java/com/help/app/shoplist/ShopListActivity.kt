package com.help.app.shoplist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.help.app.shoplist.model.ShopItemInfo
import com.help.app.shoplist.screen.ShopScreen
import com.help.app.shoplist.ui.theme.BottomCardColor
import com.help.app.shoplist.ui.theme.ShoplistTheme
import com.help.app.shoplist.ui.theme.TopCardColor

class ShopListActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoplistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShopScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun Preview() {
    ShoplistTheme {
        ShopScreen()
    }
}