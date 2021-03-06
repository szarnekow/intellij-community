/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.util;

import com.intellij.openapi.components.RoamingType;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Sergey.Malenkov
 */
@State(
  name = "WindowStateApplicationService",
  storages = @Storage(file = StoragePathMacros.APP_CONFIG + "/window.state.xml", roamingType = RoamingType.DISABLED)
)
final class WindowStateApplicationService extends WindowStateServiceImpl {
  @Override
  Point getDefaultLocationOn(GraphicsDevice screen, @NotNull String key) {
    //  backward compatibility when this service is used instead of DimensionService
    return DimensionService.getInstance().getLocation(key);
  }

  @Override
  Dimension getDefaultSizeOn(GraphicsDevice screen, @NotNull String key) {
    //  backward compatibility when this service is used instead of DimensionService
    return DimensionService.getInstance().getSize(key);
  }

  @Override
  Rectangle getDefaultBoundsOn(GraphicsDevice screen, @NotNull String key) {
    Point location = getDefaultLocationOn(screen, key);
    if (location == null) {
      return null;
    }
    Dimension size = getDefaultSizeOn(screen, key);
    if (size == null) {
      return null;
    }
    return new Rectangle(location, size);
  }

  @Override
  boolean getDefaultMaximizedOn(GraphicsDevice screen, @NotNull String key) {
    //  backward compatibility when this service is used instead of DimensionService
    return Frame.MAXIMIZED_BOTH == DimensionService.getInstance().getExtendedState(key);
  }
}
