package food2you.hp.shoitout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import food2you.hp.shoitout.adapters.ProfileScreenHorizontalListAdapter;
import food2you.hp.shoitout.model.ModelHorizontalList;
import food2you.hp.shoitout.model.testData;

public class Profile extends AppCompatActivity implements RecyclerView.OnItemTouchListener{


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager_VERTICAL,layoutManager_HORIZONTAL;
    private static RecyclerView recyclerView_VERTICAL,recyclerView_HORIZONTAL;
    private static ArrayList<ModelHorizontalList> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    TextView create_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        recyclerView_HORIZONTAL = (RecyclerView) findViewById(R.id.my_recycler_view_profile);

        recyclerView_HORIZONTAL.setHasFixedSize(true);





      /*  layoutManager_HORIZONTAL = new HorizontalScrollView(getActivity());
        recyclerView_VERTICAL.setLayoutManager(layoutManager_VERTICAL);
        recyclerView_VERTICAL.setItemAnimator(new DefaultItemAnimator());
*/


        LinearLayoutManager layoutManager = new LinearLayoutManager(Profile.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_HORIZONTAL.setLayoutManager(layoutManager);




        data = new ArrayList<ModelHorizontalList>();
        for (int i = 0; i < testData.NameEvent.length; i++) {
            data.add(new ModelHorizontalList(
                    testData.NameEvent[i],

                    testData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new ProfileScreenHorizontalListAdapter(data, Profile.this);
        recyclerView_HORIZONTAL.setAdapter(adapter);


    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
