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

public class JuegoDetalleActivity extends AppCompatActivity
        implements EventListener<DocumentSnapshot> {

    private static final String TAG = "JuegoDetail";

    public static final String KEY_JUEGO_ID = "key_juego_id";

    @BindView(R.id.juego_imagen)
    ImageView mImagenView;

    @BindView(R.id.juego_puntuacion)
    MaterialRatingBar mPuntuacionIndicador;

    @BindView(R.id.juego_nombre)
    TextView mNombreView;

    @BindView(R.id.juego_descripcion)
    TextView mDescripcionView;

    @BindView(R.id.juego_desarrolladora)
    TextView mDesarrolladoraView;

    @BindView(R.id.juego_lanzamiento)
    TextView mLanzamientoView;

    @BindView(R.id.juego_genero)
    TextView mgeneroView;

    @BindView(R.id.juego_website)
    TextView mWebsiteView;


    private FirebaseFirestore mFirestore;
    private DocumentReference mJuegoRef;
    private ListenerRegistration mJuegoRegistration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_detail);
        ButterKnife.bind(this);

        // Get juego ID from extras
        String juegoId = getIntent().getExtras().getString(KEY_JUEGO_ID);
        if (juegoId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_JUEGO_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference juego
        mJuegoRef = mFirestore.collection("juegos").document(juegoId);


    }

    @Override
    public void onStart() {
        super.onStart();


        mJuegoRegistration = mJuegoRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();


        if (mJuegoRegistration != null) {
            mJuegoRegistration.remove();
            mJuegoRegistration = null;
        }
    }

    private Task<Void> addRating(final DocumentReference mJuegoRef, final Rating rating) {
        // TODO(developer): Implement
        return Tasks.forException(new Exception("not yet implemented"));
    }

    /**
     * Listener for the Juego document ({@link #mJuegoRef}).
     */
    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "juego:onEvent", e);
            return;
        }

        onJuegoLoaded(snapshot.toObject(Juego.class));
    }

    private void onJuegoLoaded(Juego juego) {
        mPuntuacionIndicador.setRating(juego.getPuntuacion());
        mNombreView.setText(juego.getNombre());
        mDescripcionView.setText(juego.getDescripcion());
        mDesarrolladoraView.setText(juego.getDesarrolladora());
        mLanzamientoView.setText(juego.getLanzamiento());
        mgeneroView.setText(juego.getGenero());
        mWebsiteView.setText(juego.getWeb());


        // Background image
        Glide.with(mImagenView.getContext())
                .load(juego.getFoto())
                .into(mImagenView);
    }

    @OnClick(R.id.juego_button_back)
    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

}
