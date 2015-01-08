/**
 * Copyright (C) 2012 Twitter Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.twitter.finatra

import com.twitter.app.App
import com.twitter.finagle.{Http, NullServer, ListeningServer}
import com.twitter.finagle.http.HttpMuxer

trait AdminHttpServer { self: App =>

  @volatile protected var adminHttpServer: ListeningServer = NullServer

  premain {
    adminHttpServer = Http.serve(config.adminPort(), HttpMuxer)
  }

  onExit {
    adminHttpServer.close()
  }
}