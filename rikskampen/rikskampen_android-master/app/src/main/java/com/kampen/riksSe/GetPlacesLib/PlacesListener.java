package com.kampen.riksSe.GetPlacesLib;

import java.util.List;



public interface PlacesListener {
    void onPlacesFailure(PlacesException e);

    void onPlacesStart();

    void onPlacesSuccess(List<Place> places);

    void onPlacesFinished();
}