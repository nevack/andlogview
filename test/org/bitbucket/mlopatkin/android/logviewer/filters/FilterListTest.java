/*
 * Copyright 2014 Mikhail Lopatkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitbucket.mlopatkin.android.logviewer.filters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FilterListTest {

    private static class Filter1 extends Filter {

    }

    private static class Filter2 extends Filter1 {

    }

    private static class Filter3 extends Filter {

    }

    private final Filter1 filter1 = new Filter1();
    private final Filter2 filter2 = new Filter2();
    private final Filter3 filter3 = new Filter3();

    @Test
    public void testGetFiltersFor_returnsEmptyList() throws Exception {
        FilterList filterList = new FilterList();

        Assert.assertEquals(Collections.<Filter1>emptyList(),
                filterList.getFiltersFor(Filter1.class));
        Assert.assertEquals(Collections.<Filter2>emptyList(),
                filterList.getFiltersFor(Filter2.class));
        Assert.assertEquals(Collections.<Filter>emptyList(),
                filterList.getFiltersFor(Filter.class));
    }

    @Test
    public void testGetFiltersFor() throws Exception {
        FilterList filterList = new FilterList();
        filterList.registerFilter(filter1, Filter.class, Filter1.class);
        filterList.registerFilter(filter2, Filter.class, Filter1.class, Filter2.class);
        filterList.registerFilter(filter3, Filter.class, Filter3.class);

        Assert.assertEquals(Arrays.asList(filter1, filter2, filter3),
                filterList.getFiltersFor(Filter.class));
        Assert.assertEquals(Arrays.asList(filter1, filter2),
                filterList.getFiltersFor(Filter1.class));
        Assert.assertEquals(Arrays.asList(filter2),
                filterList.getFiltersFor(Filter2.class));
        Assert.assertEquals(Arrays.asList(filter3),
                filterList.getFiltersFor(Filter3.class));
    }

    @Test
    public void testGetFiltersFor_DoesntFlattenClasses() throws Exception {
        FilterList filterList = new FilterList();
        filterList.registerFilter(filter1, Filter.class, Filter1.class);
        filterList.registerFilter(filter2, Filter1.class, Filter2.class);
        filterList.registerFilter(filter3, Filter.class, Filter3.class);

        Assert.assertEquals(Arrays.asList(filter1, filter3),
                filterList.getFiltersFor(Filter.class));
        Assert.assertEquals(Arrays.asList(filter1, filter2),
                filterList.getFiltersFor(Filter1.class));
        Assert.assertEquals(Arrays.asList(filter2),
                filterList.getFiltersFor(Filter2.class));
        Assert.assertEquals(Arrays.asList(filter3),
                filterList.getFiltersFor(Filter3.class));
    }

    @Test
    public void testGetFiltersFor_returnsView() throws Exception {
        FilterList filterList = new FilterList();
        filterList.registerFilter(filter1, Filter.class, Filter1.class);

        List<Filter> filters = filterList.getFiltersFor(Filter.class);
        Assert.assertEquals("Sanity check", Arrays.<Filter>asList(filter1), filters);

        filterList.registerFilter(filter2, Filter.class, Filter1.class);
        Assert.assertEquals(Arrays.asList(filter1, filter2),
                filters);
    }
}