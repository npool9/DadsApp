/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 * Copyright (c) 2018 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.nathanpool.updatepanel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class YouTubeCollection extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.youtube_native_fragment, container, false);

        TextView tv1 = new TextView(getActivity());
        tv1.setText("Regular updates from: ");
        TextView tv2 = new TextView(getActivity());
        tv2.setText("1. Miami of Ohio");
        TextView tv3 = new TextView(getActivity());
        tv3.setText("2. Miami Redhawks");
        TextView tv4 = new TextView(getActivity());
        tv4.setText("3. Elon University");
        TextView tv5 = new TextView(getActivity());
        tv5.setText("4. Elon Phoenix");
        TextView tv6 = new TextView(getActivity());
        tv6.setText("5. NC State");
        TextView tv7 = new TextView(getActivity());
        tv7.setText("6. NC State Wolfpack");
        TextView tv8 = new TextView(getActivity());
        tv8.setText("7. NC State Computer Science");
        TextView tv9 = new TextView(getActivity());
        tv9.setText("8. SAS Institute");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        view.setLayoutManager(linearLayoutManager);


//        linearLayoutManager.addView(tv2);
//        linearLayoutManager.addView(tv3);
//        linearLayoutManager.addView(tv4);
//        linearLayoutManager.addView(tv5);
//        linearLayoutManager.addView(tv6);
//        linearLayoutManager.addView(tv7);
//        linearLayoutManager.addView(tv8);
//        linearLayoutManager.addView(tv9);


        Bundle arguments = getArguments();
        int playerType = arguments.getInt("playerType");

        ArrayList<String> videoIds = new ArrayList<>();
        ArrayList<TextView> tvs = new ArrayList<>();
        Controller controller = new Controller();
        String[] ids = controller.control();
        for (int i = 0; i < Controller.LEN; i++) {
            videoIds.add(ids[i]);
        }
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
        tvs.add(tv4);
        tvs.add(tv5);
        tvs.add(tv6);
        tvs.add(tv7);
        tvs.add(tv8);
        tvs.add(tv9);

        YouTubePlayerAdapter youTubePlayerAdapter = new YouTubePlayerAdapter(videoIds, this, playerType);
        view.setAdapter(youTubePlayerAdapter);

        return view;
    }
}
