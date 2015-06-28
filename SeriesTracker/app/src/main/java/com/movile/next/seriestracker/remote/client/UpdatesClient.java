package com.movile.next.seriestracker.remote.client;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.remote.service.IUpdatesService;

import retrofit.RestAdapter;

/**
 * Created by movile on 28/06/15.
 */
public class UpdatesClient {

    private static final String TAG = UpdatesClient.class.getSimpleName();
    private RestAdapter mAdapter;

    public UpdatesClient(String endpoint) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public ShowUpdate getLatest() {
        IUpdatesService service = mAdapter.create(IUpdatesService.class);
        return service.getLatest();
    }

}
