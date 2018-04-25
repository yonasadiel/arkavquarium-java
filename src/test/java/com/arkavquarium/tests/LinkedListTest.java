package com.arkavquarium.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.arkavquarium.models.LinkedList;
import org.junit.Test;

public class LinkedListTest {
    
  @Test
  public void testEmptyConstructor() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    assertNull(
      "constructor should return empty first Iterator",
      list.getFirstIterator()
    );
    assertNull(
      "constructor should return empty last Iterator ",
      list.getLastIterator()
    );
  }

  @Test
  public void testAdd() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    assertFalse(
      "add make list not empty",
      list.getFirstIterator() == null || list.getLastIterator() == null
    );
  }

  @Test
  public void testRemoveSingleList() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.remove(3);
    assertTrue(
      "remove single list should make list empty",
      list.isEmpty()
    );
  }

  @Test
  public void testIsEmpty() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    assertTrue(
      "isEmpty should return true on empty linked list",
      list.isEmpty()
    );
  }

  @Test
  public void testFalseIsEmpty() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    assertFalse(
      "isEmpty should return false on empty linked list",
      list.isEmpty()
    );
  }

  @Test
  public void testGetFirstContent() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    assertTrue(
      "getFirstContent should return first added element",
      list.getFirstContent() == 3
    );
  }

  @Test
  public void testgetFirstIterator() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    assertTrue(
      "getFirstIterator should return first added Iterator",
      list.getFirstIterator().getContent() == 3
    );
  }

  @Test
  public void testGetLastContent() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    assertTrue(
      "getLastContent should return lasst added element",
      list.getLastContent() == 5
    );
  }

  @Test
  public void testgetLastIterator() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    assertTrue(
      "getLastIterator should return last added Iterator",
      list.getLastIterator().getContent() == 5
    );
  }

  @Test
  public void testGetContentAt() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    list.add(8);
    assertTrue(
      "getContentAt should return exact position",
      list.getContentAt(0) == 3
       && list.getContentAt(1) == 5
       && list.getContentAt(2) == 8
    );
  }

  @Test
  public void testFindIndexResult() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(3);
    list.add(5);
    list.add(8);
    assertTrue(
      "find index result should be correspond to get",
      list.getContentAt(list.find(8)) == 8
       && list.getContentAt(list.find(5)) == 5
       && list.getContentAt(list.find(3)) == 3);
  }
}