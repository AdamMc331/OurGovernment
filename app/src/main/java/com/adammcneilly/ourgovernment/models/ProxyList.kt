package com.adammcneilly.ourgovernment.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Proxy into a list of objects.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
open class ProxyList<T: BaseModel> : BaseModel, MutableList<T> {

    constructor(): super()

    constructor(source: Parcel): super(source) {
        if(source.readInt() != 0) {
            source.readList(items, javaClass.classLoader)
        }
    }

    val items: ArrayList<T> = ArrayList()

    override fun add(element: T): Boolean {
        return items.add(element)
    }

    override fun add(index: Int, element: T) {
        return items.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return items.addAll(index, elements)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return items.addAll(elements)
    }

    override fun clear() {
        items.clear()
    }

    override fun listIterator(): MutableListIterator<T> {
        return items.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return items.listIterator(index)
    }

    override fun remove(element: T): Boolean {
        return items.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return items.removeAll(elements)
    }

    override fun removeAt(index: Int): T {
        return items.removeAt(index)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return items.retainAll(elements)
    }

    override fun set(index: Int, element: T): T {
        return items.set(index, element)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        return items.subList(fromIndex, toIndex)
    }

    override val size: Int
        get() = items.size

    override fun contains(element: T): Boolean {
        return items.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return items.containsAll(elements)
    }

    override fun get(index: Int): T {
        return items[index]
    }

    override fun indexOf(element: T): Int {
        return items.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    override fun lastIndexOf(element: T): Int {
        return items.lastIndexOf(element)
    }

    override fun iterator(): MutableIterator<T> {
        return items.iterator()
    }

    override fun equals(other: Any?): Boolean {
        return (other is ProxyList<*>) && items == other.items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }

    companion object {
        // Can't use our inline fun in ParcelableUtils because this is generic.
        //TODO: Create a generic one.
        @JvmField
        val CREATOR: Parcelable.Creator<ProxyList<*>> = object : Parcelable.Creator<ProxyList<*>> {
            override fun createFromParcel(source: Parcel): ProxyList<*> {
                return ProxyList<BaseModel>(source)
            }

            override fun newArray(size: Int): Array<ProxyList<*>?> {
                return kotlin.arrayOfNulls(size)
            }
        }
    }
}