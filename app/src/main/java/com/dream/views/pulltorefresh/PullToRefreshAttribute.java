package com.dream.views.pulltorefresh;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.slib.pulltoviews.PullToRefreshAdapterViewBase;
import com.slib.pulltoviews.PullToRefreshBase;

import org.robobinding.BindingContext;
import org.robobinding.attribute.ChildAttributeResolverMappings;
import org.robobinding.attribute.ResolvedGroupAttributes;
import org.robobinding.viewattribute.grouped.ChildViewAttributesBuilder;
import org.robobinding.viewattribute.grouped.GroupedViewAttribute;
import org.robobinding.widget.adapterview.DataSetAdapter;
import org.robobinding.widget.adapterview.DataSetAdapterBuilder;
import org.robobinding.widget.adapterview.ItemLayoutAttributeFactory;
import org.robobinding.widget.adapterview.ItemMappingAttribute;
import org.robobinding.widget.adapterview.ItemMappingUpdater;
import org.robobinding.widget.adapterview.RowLayoutAttributeAdapter;
import org.robobinding.widget.adapterview.SourceAttribute;

import static org.robobinding.attribute.ChildAttributeResolvers.predefinedMappingsAttributeResolver;
import static org.robobinding.attribute.ChildAttributeResolvers.propertyAttributeResolver;
import static org.robobinding.attribute.ChildAttributeResolvers.valueModelAttributeResolver;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_LAYOUT;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_MAPPING;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.SOURCE;

/**
 * Created by yangll on 15/9/3.
 */
public class PullToRefreshAttribute<T extends PullToRefreshAdapterViewBase> implements GroupedViewAttribute<T> {

    protected DataSetAdapterBuilder dataSetAdapterBuilder;

    @Override
    public String[] getCompulsoryAttributes() {
        return new String[]{SOURCE, ITEM_LAYOUT};
    }

    @Override
    public void mapChildAttributeResolvers(ChildAttributeResolverMappings childAttributeResolverMappings) {
        childAttributeResolverMappings.map(valueModelAttributeResolver(), SOURCE);
        childAttributeResolverMappings.map(propertyAttributeResolver(), ITEM_LAYOUT);
        childAttributeResolverMappings.map(predefinedMappingsAttributeResolver(), ITEM_MAPPING);
    }

    @Override
    public void validateResolvedChildAttributes(ResolvedGroupAttributes resolvedGroupAttributes) {
    }

    @Override
    public void setupChildViewAttributes(T view, ChildViewAttributesBuilder<T> childViewAttributesBuilder, BindingContext bindingContext) {
        dataSetAdapterBuilder = new DataSetAdapterBuilder(bindingContext);

        childViewAttributesBuilder.add(SOURCE, new SourceAttribute(dataSetAdapterBuilder));
        childViewAttributesBuilder.add(ITEM_LAYOUT, new RowLayoutAttributeAdapter(new ItemLayoutAttributeFactory(((AdapterView<?>)view.getRefreshableView()), dataSetAdapterBuilder)));

        if (childViewAttributesBuilder.hasAttribute(ITEM_MAPPING))
            childViewAttributesBuilder.add(ITEM_MAPPING, new ItemMappingAttribute(new ItemMappingUpdater(dataSetAdapterBuilder)));
    }

    @Override
    public void postBind(T t, BindingContext bindingContext) {
        DataSetAdapter<?> dataSetAdapter = dataSetAdapterBuilder.build();
        t.setAdapter(dataSetAdapter);
    }
}
