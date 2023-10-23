import { WebPlugin } from '@capacitor/core';

import type { MockDetectorPlugin, MockDetectorResult } from './definitions';

export class MockDetectorWeb extends WebPlugin implements MockDetectorPlugin {
  async detectMock(): Promise<MockDetectorResult> {
    return {
      isMock: false,
      message: 'Device is secure',
    };
  }
}
