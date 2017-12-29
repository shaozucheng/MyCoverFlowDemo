package com.coverflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {

    private CoverFlowAdapter mAdapter;
    private ArrayList<GameEntity> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;
    FeatureCoverFlow mCoverFlow;

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.jump);

        mData.add(new GameEntity(R.drawable.image_1, R.string.title1));
        mData.add(new GameEntity(R.drawable.image_2, R.string.title2));
        mData.add(new GameEntity(R.drawable.image_3, R.string.title3));
        mData.add(new GameEntity(R.drawable.image_4, R.string.title4));
        mData.add(new GameEntity(R.drawable.item2, R.string.title4));
        mData.add(new GameEntity(R.drawable.item3, R.string.title4));
        mData.add(new GameEntity(R.drawable.item4, R.string.title4));
        mData.add(new GameEntity(R.drawable.item5, R.string.title4));
        mData.add(new GameEntity(R.drawable.item6, R.string.title4));

        mTitle = (TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });
//        final Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
//        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
//        mTitle.setInAnimation(in);
//        mTitle.setOutAnimation(out);

        mAdapter = new CoverFlowAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = view.findViewById(R.id.image);
                if (imageView != null) {
                   // mCoverFlow.scrollToPosition();
                    GameEntity entity = (GameEntity) imageView.getTag();
                    Toast.makeText(MainActivity.this, entity.titleResId, Toast.LENGTH_SHORT).show();
                }

            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

}
