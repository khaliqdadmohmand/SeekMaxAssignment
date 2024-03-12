package com.loc.seekmax.presentation.home

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.loc.seekmax.MainActivity
import com.loc.seekmax.R
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.presentation.Dimens.MediumPadding1
import com.loc.seekmax.presentation.common.ArticlesList
import com.loc.seekmax.presentation.common.SearchBar
import com.loc.seekmax.presentation.navgraph.NavGraph
import com.loc.seekmax.presentation.navgraph.Route


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
    navigateToProfile: () -> Unit
) {

    val context = LocalContext.current

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {


//        Spacer(modifier = Modifier.height(MediumPadding1))

        Row(modifier = Modifier
            .padding(horizontal = MediumPadding1)
            .fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape).clickable  {
                        navigateToProfile()
                    }

            )
            SearchBar(
                modifier = Modifier
                    .padding(start = MediumPadding1)
                    .fillMaxWidth(),
                text = "",
                readOnly = true,
                onValueChange = {},
                onSearch = {},
                onClick = {
                    navigateToSearch()
                }
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

//        Text(
//            text = titles, modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = MediumPadding1)
//                .basicMarquee(), fontSize = 12.sp,
//            color = colorResource(id = R.color.placeholder)
//        )
//
//        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}