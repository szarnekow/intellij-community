package com.intellij.lang.properties.editor;

import com.intellij.ide.structureView.FileEditorPositionListener;
import com.intellij.ide.structureView.ModelListener;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.lang.properties.ResourceBundle;
import com.intellij.lang.properties.structureView.GroupByWordPrefixes;
import com.intellij.lang.properties.structureView.PropertiesSeparatorManager;
import com.intellij.openapi.project.Project;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: Feb 10, 2005
 * Time: 3:13:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceBundleStructureViewModel implements PropertiesGroupingStructureViewModel {
  private final Project myProject;
  private final ResourceBundle myResourceBundle;
  private final GroupByWordPrefixes myGroupByWordPrefixes;

  public ResourceBundleStructureViewModel(final Project project, ResourceBundle root) {
    myProject = project;
    myResourceBundle = root;
    String separator = PropertiesSeparatorManager.getInstance().getSeparator(project, new ResourceBundleAsVirtualFile(myResourceBundle));
    myGroupByWordPrefixes = new GroupByWordPrefixes(separator);
  }

  public void setSeparator(String separator) {
    myGroupByWordPrefixes.setSeparator(separator);
    PropertiesSeparatorManager.getInstance().setSeparator(new ResourceBundleAsVirtualFile(myResourceBundle), separator);
  }

  public String getSeparator() {
    return myGroupByWordPrefixes.getSeparator();
  }

  public StructureViewTreeElement getRoot() {
    return new ResourceBundleFileStructureViewElement(myProject, myResourceBundle);
  }

  public Grouper[] getGroupers() {
    return new Grouper[]{myGroupByWordPrefixes};
  }

  public Sorter[] getSorters() {
    return new Sorter[] {Sorter.ALPHA_SORTER};
  }

  public Filter[] getFilters() {
    return new Filter[0];
  }

  public Object getCurrentEditorElement() {
    return null;
  }

  public void addEditorPositionListener(FileEditorPositionListener listener) {

  }

  public void removeEditorPositionListener(FileEditorPositionListener listener) {

  }

  public void addModelListener(ModelListener modelListener) {

  }

  public void removeModelListener(ModelListener modelListener) {

  }

  public void dispose() {

  }
}
