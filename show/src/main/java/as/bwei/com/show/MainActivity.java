package as.bwei.com.show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.gson.Gson;

import java.util.List;

import as.bwei.com.show.adapter.WhAdapter;
import as.bwei.com.show.bean.WhBean;
import as.bwei.com.show.presenter.ShowPresenter;
import as.bwei.com.show.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView{

    private RecyclerView recy_view;
    private boolean a=true;
    private ImageView image_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy_view = (RecyclerView) findViewById(R.id.recy_view);
        image_btn = (ImageView) findViewById(R.id.image_btn);

        recy_view.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        ShowPresenter showPresenter = new ShowPresenter(MainActivity.this);
        showPresenter.show();

        //布局样式切换
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a){
                    recy_view.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                    ShowPresenter presenter = new ShowPresenter(MainActivity.this);
                    presenter.show();
                    a=false;
                }else {
                    recy_view.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
                    ShowPresenter presenter = new ShowPresenter(MainActivity.this);
                    presenter.show();
                    a=true;
                }
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

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                WhBean bean = gson.fromJson(msg, WhBean.class);
                List<WhBean.DataBean> data = bean.getData();
                WhAdapter whAdapter = new WhAdapter(MainActivity.this,data);
                recy_view.setAdapter(whAdapter);
            }
        });
    }
}
