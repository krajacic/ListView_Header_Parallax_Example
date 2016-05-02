package cawabanga.com.listviewheaderparallax;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private static final int MAX_ROWS = 50;
    private int lastTopValue = 0;

    private List<String> modelList = new ArrayList<>();
    private ListView listView;
    private ImageView headerImage;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < MAX_ROWS; i++) {
            modelList.add("List item " + i);
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_row, modelList);
        listView.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.activity_header, listView, false);
        listView.addHeaderView(header, null, false);

        headerImage = (ImageView) header.findViewById(R.id.HeaderimageView);
        listView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        Rect rect = new Rect();
        headerImage.getLocalVisibleRect(rect);
        if (lastTopValue != rect.top){
            lastTopValue = rect.top;
            headerImage.setY((float)(rect.top/2.0));
        }
    }
}
