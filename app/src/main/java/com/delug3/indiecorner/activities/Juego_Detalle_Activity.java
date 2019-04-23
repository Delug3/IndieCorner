/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.delug3.indiecorner.activities;

import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.delug3.indiecorner.R;
import com.delug3.indiecorner.model.Juego;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class Juego_Detalle_Activity extends AppCompatActivity
        implements EventListener<DocumentSnapshot> {

    private static final String TAG = "RestaurantDetail";

    public static final String KEY_RESTAURANT_ID = "key_restaurant_id";

    @BindView(R.id.restaurant_image)
    ImageView mImageView;

    @BindView(R.id.restaurant_name)
    TextView mNameView;

    @BindView(R.id.restaurant_rating)
    MaterialRatingBar mRatingIndicator;

    @BindView(R.id.restaurant_num_ratings)
    TextView mNumRatingsView;

    @BindView(R.id.restaurant_city)
    TextView mCityView;

    @BindView(R.id.restaurant_category)
    TextView mCategoryView;

    @BindView(R.id.restaurant_price)
    TextView mPriceView;

    @BindView(R.id.view_empty_ratings)
    ViewGroup mEmptyView;

    @BindView(R.id.recycler_ratings)
    RecyclerView mRatingsRecycler;


    private FirebaseFirestore mFirestore;
    private DocumentReference mRestaurantRef;
    private ListenerRegistration mRestaurantRegistration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        ButterKnife.bind(this);

        // Get restaurant ID from extras
        String restaurantId = getIntent().getExtras().getString(KEY_RESTAURANT_ID);
        if (restaurantId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_RESTAURANT_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the restaurant
        mRestaurantRef = mFirestore.collection("juegos").document(restaurantId);


    }

    @Override
    public void onStart() {
        super.onStart();


        mRestaurantRegistration = mRestaurantRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();


        if (mRestaurantRegistration != null) {
            mRestaurantRegistration.remove();
            mRestaurantRegistration = null;
        }
    }

    private Task<Void> addRating(final DocumentReference restaurantRef, final Rating rating) {
        // TODO(developer): Implement
        return Tasks.forException(new Exception("not yet implemented"));
    }

    /**
     * Listener for the Juego document ({@link #mRestaurantRef}).
     */
    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "restaurant:onEvent", e);
            return;
        }

        onRestaurantLoaded(snapshot.toObject(Juego.class));
    }

    private void onRestaurantLoaded(Juego juego) {
        mNameView.setText(juego.getNombre());

        // Background image
        Glide.with(mImageView.getContext())
                .load(juego.getFoto())
                .into(mImageView);
    }

    @OnClick(R.id.restaurant_button_back)
    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

}
