package com.example.adminblinkitclone.view.main.cards


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.data.ProductItems

@Composable
fun ItemDesign(
    mainProductData: ProductItems,
    onProductEditClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(315.dp)
            .width(240.dp)
            .background(
                shape = RoundedCornerShape(17.dp),
                color = Color.Transparent
            ),
    ) {
        AsyncImage(
            model = mainProductData.productImages,
            contentDescription = "image for the product",
            modifier = Modifier
                .fillMaxWidth()
                .size(140.dp)
        )
        Spacer(modifier= Modifier.height(28.dp))
        Text(
            text = mainProductData.productTitle ,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
        Text(
            text = "(${mainProductData.productRating})" ,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Rs. ${mainProductData.priceInRs}",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 7.dp, vertical = 3.dp)
                    .weight(1f)
            )

            IconButton(
                modifier = Modifier.size(70.dp)
                    .padding(top = 3.dp, end = 8.dp, start = 5.dp, bottom = 9.dp)
                    .background(
                        shape = RoundedCornerShape(18.dp),
                        color = Color.White
                    ),
                onClick = {
                    onProductEditClick()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_edit),
                    tint = Color.Black,
                    contentDescription = "",
                )
            }

        }
    }
}

@Preview
@Composable
private fun ItemdesignPreview() {}