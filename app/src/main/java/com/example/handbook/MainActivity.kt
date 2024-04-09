package com.example.handbook

import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationView
import android.view.Menu
import android.widget.Adapter
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

        var list = ArrayList<ListItem>()

        var rcView = findViewById<RecyclerView>(R.id.rcView)

        nav_view.setItemIconTintList(null);

        //list.add(ListItem(R.drawable.body_car, "кузов", "кузов машины"))
        //list.add(ListItem(R.drawable.engine, "двигатель", "двигатель машины"))
        //list.add(ListItem(R.drawable.disk, "диски", "диски машины"))

        //ошибка в данных строках 47-53?
        list.addAll(fillArrays(resources.getStringArray(R.array.bodycar),
            resources.getStringArray(R.array.bodycar_content),
            getImageId(R.array.body_car_image)))

        list.addAll(fillArrays(resources.getStringArray(R.array.engine),
            resources.getStringArray(R.array.engine_content),
            getImageId(R.array.engine_image)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list, this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            //ошибка в строках 66-69 и тд?
            R.id.id_body_car ->
            {
                Toast.makeText(this, "Нажат кузов", Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.bodycar),
                    resources.getStringArray(R.array.bodycar_content),
                    getImageId(R.array.body_car_image)))
            }
            R.id.id_engine ->
            {
                Toast.makeText(this, "Нажат двигатель", Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.engine),
                    resources.getStringArray(R.array.engine_content),
                    getImageId(R.array.engine_image)))
            }
            R.id.id_transmission -> Toast.makeText(this, "Нажат 3", Toast.LENGTH_SHORT).show()
            R.id.id_electrical -> Toast.makeText(this, "Нажат 4", Toast.LENGTH_SHORT).show()
            R.id.id_cooling -> Toast.makeText(this, "Нажат 5", Toast.LENGTH_SHORT).show()
            R.id.id_chassis -> Toast.makeText(this, "Нажат 6", Toast.LENGTH_SHORT).show()
            R.id.id_steering -> Toast.makeText(this, "Нажат 7", Toast.LENGTH_SHORT).show()
            R.id.id_brake_system -> Toast.makeText(this, "Нажат тормоза", Toast.LENGTH_SHORT).show()
            R.id.id_heating_conditioning -> Toast.makeText(this, "Нажат 9", Toast.LENGTH_SHORT).show()
            R.id.id_salon -> Toast.makeText(this, "Нажат салон", Toast.LENGTH_SHORT).show()
            R.id.id_disk -> Toast.makeText(this, "Нажат диски", Toast.LENGTH_SHORT).show()
            R.id.id_аdditional_equipment -> Toast.makeText(this, "Нажат доп", Toast.LENGTH_SHORT).show()
            R.id.id_salon2 -> Toast.makeText(this, "Нажат салон 2", Toast.LENGTH_SHORT).show()
        }
        return true
    }
    fun fillArrays(titleArray: Array<String>, contentArray: Array<String>, imageArray: IntArray):List<ListItem>{
        var listItemArray = ArrayList<ListItem>()
        for(n in 0..titleArray.size - 1){
            var listItem = ListItem(imageArray[n], titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        //drawer_main_menu.closeDrawer(nav_view)  //горит красным, как добавить эту строчку?
        return listItemArray
    }

    fun getImageId(imageArrayId:Int): IntArray{
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices){
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }
}






