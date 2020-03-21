//package net.cachapa.expandablelayout.demo;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.dale.libdemo.R;
//
//import net.cachapa.expandablelayout.ExpandableLayout;
//
//public class AccordionFragment extends Fragment implements View.OnClickListener {
//
//    private ExpandableLayout expandableLayout0;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.accordion_fragment, container, false);
//
//        expandableLayout0 = rootView.findViewById(R.id.expandable_layout_0);
//
//        expandableLayout0.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
//            @Override
//            public void onExpansionUpdate(float expansionFraction, int state) {
//                Log.d("ExpandableLayout0", "State: " + state);
//            }
//        });
//
//
//        rootView.findViewById(R.id.expand_button_0).setOnClickListener(this);
//
//        return rootView;
//    }
//
//    boolean kai = false;
//
//    @Override
//    public void onClick(View view) {
//        if (kai) {
//            kai = false;
//            expandableLayout0.collapse();
//
//        } else {
//            expandableLayout0.expand();
//            kai = true;
//        }
//    }
//}
