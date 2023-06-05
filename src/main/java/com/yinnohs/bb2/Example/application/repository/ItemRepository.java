package com.yinnohs.bb2.Example.application.repository;

import com.yinnohs.bb2.Example.application.enums.ItemState;
import com.yinnohs.bb2.Example.application.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByItemState (ItemState state);
}
