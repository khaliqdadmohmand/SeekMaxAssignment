package com.loc.seekmax.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.loc.seekmax.R
import com.loc.seekmax.presentation.Dimens
import com.loc.seekmax.presentation.auth.AuthEvent
import com.loc.seekmax.presentation.common.SeekButton
import com.loc.seekmax.presentation.common.SeekTextButton
import com.loc.seekmax.presentation.profile.component.Header
import com.loc.seekmax.presentation.profile.component.InformationCard
import com.loc.seekmax.presentation.profile.component.ProfileAvatar
import kotlin.reflect.KSuspendFunction0

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    event: (AuthEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePhone = viewModel::onChangePhone,
        onSaveUserInfo = viewModel::onSaveUserInfo,
        event = event,
        navigateUp = navigateUp
    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePhone: (String) -> Unit,
    onSaveUserInfo: () -> Unit,
    event: (AuthEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            event = event,
            onBackClick = navigateUp
        )
        Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))

        ProfileAvatar(
            painter = rememberAsyncImagePainter(model = state.image),
            size = 128
        )
        Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))

        SeekTextButton(text = stringResource(R.string.change_profile_picture)) {

        }
        Spacer(modifier = Modifier.width(Dimens.MediumPadding3))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1F)) {
                InformationCard(
                    title = "First Name",
                    information = state.firstname,
                    onTextChange = onChangeFirstName
                )
            }
            Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))
            Box(modifier = Modifier.weight(1F)) {
                InformationCard(
                    title = "Last Name",
                    information = state.lastname,
                    onTextChange = onChangeLastName
                )
            }
        }
        InformationCard(
            title = "Email",
            information = state.email,
            onTextChange = onChangeEmail
        )
        InformationCard(
            title = "Phone",
            information = state.phone,
            onTextChange = onChangePhone
        )

        Spacer(modifier = Modifier.weight(1F))
        SeekButton(text = "Save", onClick = { onSaveUserInfo() })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(event = {}, navigateUp = {})
}