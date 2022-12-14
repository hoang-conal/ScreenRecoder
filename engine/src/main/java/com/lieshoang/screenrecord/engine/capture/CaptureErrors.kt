/**
 * Designed and developed by Aidan Follestad (@afollestad)
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
package com.lieshoang.screenrecord.engine.capture

class FileSystemException(base: Exception) :
    Exception(
        "Recorder was unable to access your file system. You may need to change your " +
            "recording folder in Recorder's settings. ${base.displayMessage()}",
        base
    )

class PrepareFailedException(base: Throwable) :
    Exception(
        "Recorder was unable to prepare for recording. ${base.displayMessage()}",
        base
    )

class StartRecordingException(base: Exception) :
    Exception(
        "Recorder was unable to begin recording. ${base.displayMessage()}",
        base
    )

internal fun Throwable.displayMessage(): String {
  return if (!this.message.isNullOrBlank()) {
    this.message!!
  } else {
    "$this"
  }
}
