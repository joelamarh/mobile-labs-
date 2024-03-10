package com.example.level_3_task_2.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.level_3_task_2.model.Portal

class PoralView: ViewModel() {

    var portals = mutableStateListOf<Portal>(
        Portal("HvA", "https://hva.nl"),
        Portal("google", "https://google.nl"),
        Portal("sis", "https://sis.nl")
    )

    fun addPortal(portal: Portal) {
        portals.add(portal);
    }
}