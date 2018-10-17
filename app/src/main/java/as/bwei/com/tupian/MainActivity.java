package as.bwei.com.tupian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import as.bwei.com.tupian.adapter.MyAdapter;
import as.bwei.com.tupian.bean.ShowBean;
import as.bwei.com.tupian.presenter.ShowPresenter;
import as.bwei.com.tupian.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView{

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL));
        ShowPresenter presenter = new ShowPresenter(MainActivity.this);
        presenter.show();
    }

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(msg, ShowBean.class);
                List<ShowBean.DataBean> data = showBean.getData();
                MyAdapter adapter = new MyAdapter(MainActivity.this,data);
                recycler_view.setAdapter(adapter);
            }
        });
    }

    @Override
    public void failure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
