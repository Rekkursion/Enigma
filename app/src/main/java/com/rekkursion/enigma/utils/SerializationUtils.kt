package com.rekkursion.enigma.utils

import java.io.*

@Suppress("UNCHECKED_CAST")
object SerializationUtils {
    // serialize
    @Synchronized fun serialize(obj: Any?, path: String): Boolean {
        if (obj == null)
            return false

        var oos: ObjectOutputStream? = null
        try {
            oos = ObjectOutputStream(FileOutputStream(path))
            oos.writeObject(obj)
            oos.close()
            return true
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (oos != null) {
                try {
                    oos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        return false
    }

    // de-serialize
    @SuppressWarnings("unchecked")
    @Synchronized fun<T> deSerialize(path: String): T? {
        var ois: ObjectInputStream? = null
        try {
            ois = ObjectInputStream(FileInputStream(path))
            val ret: T? = ois.readObject() as T?
            ois.close()
            return ret
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            ois?.close()
        }

        return null
    }
}