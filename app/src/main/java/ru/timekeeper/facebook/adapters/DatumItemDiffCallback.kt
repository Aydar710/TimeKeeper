package ru.timekeeper.facebook.adapters

import android.support.v7.util.DiffUtil
import ru.timekeeper.data.network.model.facebook.grouplistremote.Datum

class DatumItemDiffCallback : DiffUtil.ItemCallback<Datum>(){

    override fun areItemsTheSame(oldGroup: Datum, newGroup: Datum): Boolean =
            oldGroup.id == newGroup.id

    override fun areContentsTheSame(oldGroup: Datum, newGroup: Datum): Boolean =
            oldGroup == newGroup
}
