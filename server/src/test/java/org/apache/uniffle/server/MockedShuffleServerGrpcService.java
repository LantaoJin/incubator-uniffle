/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.uniffle.server;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.uniffle.proto.RssProtos;


public class MockedShuffleServerGrpcService extends ShuffleServerGrpcService {

  private static final Logger LOG = LoggerFactory.getLogger(MockedShuffleServerGrpcService.class);

  private long mockedTimeout = -1L;

  public void enableMockedTimeout(long timeout) {
    mockedTimeout = timeout;
  }

  public void disableMockedTimeout() {
    mockedTimeout = -1;
  }

  public MockedShuffleServerGrpcService(ShuffleServer shuffleServer) {
    super(shuffleServer);
  }

  @Override
  public void sendShuffleData(RssProtos.SendShuffleDataRequest request,
      StreamObserver<RssProtos.SendShuffleDataResponse> responseObserver) {
    if (mockedTimeout > 0) {
      LOG.info("Add a mocked timeout on sendShuffleData");
      Uninterruptibles.sleepUninterruptibly(mockedTimeout, TimeUnit.MILLISECONDS);
    }
    super.sendShuffleData(request, responseObserver);
  }

  @Override
  public void reportShuffleResult(RssProtos.ReportShuffleResultRequest request,
                              StreamObserver<RssProtos.ReportShuffleResultResponse> responseObserver) {
    if (mockedTimeout > 0) {
      LOG.info("Add a mocked timeout on reportShuffleResult");
      Uninterruptibles.sleepUninterruptibly(mockedTimeout, TimeUnit.MILLISECONDS);
    }
    super.reportShuffleResult(request, responseObserver);
  }

  @Override
  public void getShuffleResult(RssProtos.GetShuffleResultRequest request,
                               StreamObserver<RssProtos.GetShuffleResultResponse> responseObserver) {
    if (mockedTimeout > 0) {
      LOG.info("Add a mocked timeout on getShuffleResult");
      Uninterruptibles.sleepUninterruptibly(mockedTimeout, TimeUnit.MILLISECONDS);
    }
    super.getShuffleResult(request, responseObserver);
  }
}
