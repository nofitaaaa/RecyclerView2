package id.sch.smktelkom_mlg.learn.recycleview2;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.recycleview2.adapter.HotelAdapter;
import id.sch.smktelkom_mlg.learn.recycleview2.model.Hotel;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Hotel> mlist = new ArrayList<>();
    HotelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HotelAdapter(mlist);
        recyclerView.setAdapter(mAdapter);

        fillData();
    }

    private void fillData()
    {
        Resources resources = getResources();

        String [] arJudul = resources.getStringArray(R.array.places);
        String [] arDeskripsi = resources.getStringArray(R.array.place_desc);
        TypedArray a =resources.obtainTypedArray(R.array.places_picture);
        Drawable [] arfoto = new Drawable[a.length()];

            for (int i = 0; i < arfoto.length; i++)
            {
                BitmapDrawable bd = (BitmapDrawable) a.getDrawable(i);
                RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(getResources(),bd.getBitmap());
                rbd.setCircular(true);
                arfoto[i] = rbd;
            }

                a.recycle();

            for (int i = 0; i < arJudul.length; i++)
            {
                mlist.add( new Hotel (arJudul[i],arDeskripsi[i],arfoto[i]));
            }

                mAdapter.notifyDataSetChanged();
    }
}
