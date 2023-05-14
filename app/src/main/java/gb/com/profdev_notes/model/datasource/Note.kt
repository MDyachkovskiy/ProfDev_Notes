package gb.com.profdev_notes.model.datasource

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_note")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String?,
    var description: String?,
    var tag: String?
    ) : Parcelable