package com.keremturker.roomdatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keremturker.roomdatabase.adapter.CountryAdapter
import com.keremturker.roomdatabase.R
import com.keremturker.roomdatabase.model.Country
import com.keremturker.roomdatabase.service.CountryDao
import com.keremturker.roomdatabase.service.CountryDatabase

class MainActivity : AppCompatActivity() {


    companion object {
        lateinit var edt_country: EditText
        lateinit var edt_region: EditText
        lateinit var edt_capital: EditText
        lateinit var edt_language: EditText
        lateinit var rv_list: RecyclerView
        var uuid: Int = 0


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //initialize
        edt_country = findViewById(R.id.edt_country)
        edt_region = findViewById(R.id.edt_region)
        edt_capital = findViewById(R.id.edt_capital)
        edt_language = findViewById(R.id.edt_language)
        rv_list = findViewById(R.id.rv_list)
        val btn_insert = findViewById<Button>(R.id.btn_insert)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_list = findViewById<Button>(R.id.btn_list)
        val btn_delete = findViewById<Button>(R.id.btn_delete)


        edt_country.requestFocus()



        rv_list.layoutManager = LinearLayoutManager(this)

        val dao = CountryDatabase(applicationContext).countryDao()


        list(dao)


        btn_insert.setOnClickListener {


            val country = Country(
                edt_country.text.toString(),
                edt_region.text.toString(),
                edt_capital.text.toString(),
                edt_language.text.toString()
            )

            dao.insertAll(country).also {

                CleanScreen()
                list(dao)

                Toast.makeText(this@MainActivity, " Insert Success", Toast.LENGTH_SHORT)
                    .show()


            }
        }

        btn_list.setOnClickListener {

            list(dao)
        }

        btn_delete.setOnClickListener {

            if (uuid != 0) {

                dao.deleteCountry(uuid)

                list(dao)
                CleanScreen()

                Toast.makeText(this@MainActivity, "Delete Success", Toast.LENGTH_SHORT)
                    .show()


            } else {
                Toast.makeText(this@MainActivity, "select item from list", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        btn_update.setOnClickListener {

            if (uuid != 0) {

                var country = Country(
                    edt_country.text.toString(),
                    edt_region.text.toString(),
                    edt_capital.text.toString(),
                    edt_language.text.toString()
                )

                country.uuid = uuid

                dao.updateCountry(country)

                list(dao)

                CleanScreen()

                Toast.makeText(
                    this@MainActivity,
                    "Update Success",
                    Toast.LENGTH_SHORT
                )
                    .show()


            } else {

                Toast.makeText(this@MainActivity, "select item from list", Toast.LENGTH_SHORT)
                    .show()

            }


        }


    }


    fun CleanScreen() {
        edt_country.text.clear()
        edt_capital.text.clear()
        edt_language.text.clear()
        edt_region.text.clear()
        edt_country.requestFocus()

        uuid = 0

    }

    fun list(dao: CountryDao) {

        val ulkeler = dao.getAll()
        rv_list.adapter = CountryAdapter(ulkeler)

    }


}