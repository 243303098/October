package com.example.mapper;

import com.example.model.UIElement;
import com.example.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UIElementMapper extends MyMapper<UIElement> {

    List<UIElement> getUIElementByName(String name);

    List<UIElement> getAllUIElement();

}