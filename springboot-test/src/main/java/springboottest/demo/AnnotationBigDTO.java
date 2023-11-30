package springboottest.demo; /**
 * Copyright 2020 Tianshu AI Platform. All Rights Reserved.
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
 * =============================================================
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @description 标注DTO
 * @date 2021-01-06
 */
@Data
public class AnnotationBigDTO implements Serializable {

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;
}
