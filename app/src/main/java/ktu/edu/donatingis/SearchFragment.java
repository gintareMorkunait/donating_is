package ktu.edu.donatingis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    DatabaseReference ref;
    SearchFragment context = this;
    ArrayList<Member> list;
    ArrayList<Friend> flist;
    RecyclerView recyclerView;
    SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container,false);



        ref = FirebaseDatabase.getInstance().getReference().child("Member");
        recyclerView = root.findViewById(R.id.rv);
        searchView = root.findViewById(R.id.searchView);



/*
        recyclerView.setOnLongClickListener(new AdapterView<Member>.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                Member item = list.get(itemPosition);
                for(Member object : list)
                {
                    if(object == item)
                    {
                        list.remove(item);
                    }
                    AdapterClass adapterClass = new AdapterClass(list);
                    recyclerView.setAdapter(adapterClass);
                }
                return false;
            }
        });*/
        return root;
    }





    @Override
    public void onStart() {
        super.onStart();
        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Member.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }

                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }


        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }
    private void search(String str)
    {
        ArrayList<Member> myList = new ArrayList<>();
        for(Member object : list)
        {
            if(object.getAddress().toLowerCase().startsWith(str.toLowerCase()))
            {
                myList.add(object);
            }
            AdapterClass adapterClass = new AdapterClass(myList);
            recyclerView.setAdapter(adapterClass);
        }
    }
}

