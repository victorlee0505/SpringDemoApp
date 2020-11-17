package com.example.demo.Database.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Top {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<Country> data;
}
