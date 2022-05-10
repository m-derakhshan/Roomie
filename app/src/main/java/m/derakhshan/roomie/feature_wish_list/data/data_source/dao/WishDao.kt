package m.derakhshan.roomie.feature_wish_list.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.roomie.feature_wish_list.domain.model.WishModel


@Dao
interface WishDao {
    @Insert
    suspend fun insert(propertyId: WishModel)

    @Query("DELETE FROM WishModel")
    suspend fun deleteAllWishes()

    @Query("SELECT * FROM WishModel WHERE id =:propertyId")
    suspend fun isInWishList(propertyId: String): WishModel?

    @Query("SELECT * FROM WishModel")
    fun getWishList(): Flow<List<WishModel>>


    @Query("DELETE FROM WishModel Where id=:propertyId")
    suspend fun deleteFromWishes(propertyId: String)

}