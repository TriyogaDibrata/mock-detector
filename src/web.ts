import { WebPlugin } from '@capacitor/core';

import type { MockDetectorPlugin, MockDetectorResult } from './definitions';

export class MockDetectorWeb extends WebPlugin implements MockDetectorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async detectMock(): Promise<MockDetectorResult> {
    return {
      isMock: false,
      message: 'Device is secure',
    };
  }
}
