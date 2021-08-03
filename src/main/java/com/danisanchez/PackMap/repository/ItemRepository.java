package com.danisanchez.PackMap.repository;

import com.danisanchez.PackMap.model.Item;
import com.danisanchez.PackMap.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
