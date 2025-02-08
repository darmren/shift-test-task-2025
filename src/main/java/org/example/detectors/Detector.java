package org.example.detectors;

@FunctionalInterface
public interface Detector<T>{
    T detect(String line);
}
