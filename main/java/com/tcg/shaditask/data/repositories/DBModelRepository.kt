package com.tcg.shaditask.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.tcg.shaditask.data.db.AppDatabase
import com.tcg.shaditask.data.db.entities.DBModel
import com.tcg.shaditask.data.network.MyApi
import com.tcg.shaditask.data.network.response.DataResponse
import com.tcg.shaditask.data.network.response.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

class DBModelRepository(
    private val api: MyApi,
    private val db: AppDatabase
)  {

    //suspend fun getDBList() = db.getDBModelDao().getAllDBModel()
    suspend fun getDBList(): LiveData<List<DBModel>> {
        return withContext(Dispatchers.IO){
            db.getDBModelDao().getAllDBModel()
        }
    }

    suspend fun getAllData(){
        val result = api.getAllData()
        Log.e("Check1,","Yes")
        if (result.isSuccessful) {
            try {
                Log.e("Check2,", "Yes")
                val dataResponse: DataResponse? = result.body()
                Log.e("Check3,", "Yes")
                val resultList: List<Results>? = dataResponse?.results
                //val resultArray: JsonArray? = responseResult?.getAsJsonArray("results")
                Log.e("Check4,", "Yes")
                var dataList: ArrayList<DBModel> = ArrayList()
                var i: Int = 0
                resultList?.forEach() {
                    Log.e("Check5,", "Yes")
                    var dbModel: DBModel = DBModel(
                        "", "", "", "",
                        "", "", "", "", "", "", "",
                        "", "", "", false, false
                    )
                    dbModel.gender = resultList?.get(i)!!.gender
                    dbModel.username = resultList?.get(i)!!.login.username
                    dbModel.firstName = resultList?.get(i)!!.name.first
                    dbModel.lastName = resultList?.get(i)!!.name.last
                    dbModel.city = resultList?.get(i)!!.location.city
                    dbModel.state = resultList?.get(i)!!.location.state
                    dbModel.country = resultList?.get(i)!!.location.country
                    dbModel.postcode = resultList?.get(i)!!.location.postcode.toString()
                    dbModel.email = resultList?.get(i)!!.email
                    dbModel.age = resultList?.get(i)!!.dob.age.toString()
                    dbModel.cell = resultList?.get(i)!!.cell
                    dbModel.largePicture = resultList?.get(i)!!.picture.large
                    dbModel.mediumPicture = resultList?.get(i)!!.picture.medium
                    dbModel.thumbnail = resultList?.get(i)!!.picture.thumbnail
                    dbModel.isSurveyDone = false
                    dbModel.isAccepted = false

                    dataList.add(dbModel)
                    i++
                }

                dataList.forEach() {
                    var returnValue: Long = db.getDBModelDao().insertWithOnConflictDBModel(it)
                    if (returnValue == -1L) {
                        /*val updateSingleIsActive:Int = db.getDBModelDao().updateSingleIsActive(
                        prefs.getUserName(), true, it.SMSSendDatetime, it.ClaimRefNo
                    )*/
                        Log.e("UPDATE_COUNT", "UPDATE: -1")
                    }
                }

                Log.e("Count", dataList.size.toString())
            }catch (e:Exception){
                e.printStackTrace();
            }
        }
    }

    suspend fun updateDBModel(dbModel: DBModel) {
        //TODO("Not yet implemented")
        db.getDBModelDao().updateDBModel(dbModel)
    }
}