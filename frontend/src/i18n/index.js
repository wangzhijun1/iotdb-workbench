/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import en from './en.js';
import de from './de.js';
import cn from './cn.js';
import enLocale from 'element-plus/lib/locale/lang/en';
import deLocale from 'element-plus/lib/locale/lang/de';
import zhLocale from 'element-plus/lib/locale/lang/zh-cn';
import { createI18n } from 'vue-i18n';

let messages = {
  ...en,
  ...de,
  ...cn,
};

let langMap = {
  cn: zhLocale.name,
  de: deLocale.name,
  en: enLocale.name,
};
let locale = localStorage.getItem('lang') || 'cn';
let i18n = createI18n({
  legacy: false,
  locale: langMap[locale],
  fallbackLocale: enLocale.name,
  messages,
});

export default i18n;
