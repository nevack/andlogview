/*
 * Copyright 2020 Mikhail Lopatkin
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

package org.bitbucket.mlopatkin.android.logviewer.ui.mainframe.popupmenu;

import org.bitbucket.mlopatkin.android.logviewer.ui.logtable.PopupMenuViewImpl;
import org.bitbucket.mlopatkin.android.logviewer.widgets.ObservableAction;
import org.bitbucket.mlopatkin.utils.MyStringUtils;
import org.bitbucket.mlopatkin.utils.events.Observable;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;

public class TablePopupMenuViewImpl extends PopupMenuViewImpl implements TablePopupMenuPresenter.TablePopupMenuView {
    private static final int MAX_HEADER_LENGTH = 30;
    private static final int PREFIX_LENGTH = 5;
    private static final char ELLIPSIS = '\u2026';  // …

    private final ObservableAction bookmarkAction = new ObservableAction();
    private final JMenu quickFilterHeader = new JMenu();

    public TablePopupMenuViewImpl(JComponent owner, int x, int y) {
        super(owner, x, y);
    }

    @Override
    public void setHeader(String columnName, String headerText) {
        String header = columnName + ": " + MyStringUtils.abbreviateMiddle(headerText, ELLIPSIS, MAX_HEADER_LENGTH,
                PREFIX_LENGTH);
        quickFilterHeader.setText(header);
        popupMenu.add(quickFilterHeader).setEnabled(false);
    }

    @Override
    public Observable<Runnable> setBookmarkAction(boolean enabled, String title) {
        bookmarkAction.setEnabled(enabled);
        bookmarkAction.putValue(Action.NAME, title);
        if (popupMenu.getComponentCount() > 0) {
            popupMenu.addSeparator();
        }
        popupMenu.add(bookmarkAction);
        return bookmarkAction.asObservable();
    }

    @Override
    public Observable<Runnable> addQuickFilterAction(boolean enabled, String title) {
        ObservableAction quickFilterAction = new ObservableAction(title);
        quickFilterAction.setEnabled(enabled);
        quickFilterHeader.setEnabled(true);
        quickFilterHeader.add(quickFilterAction);
        return quickFilterAction.asObservable();
    }

}
