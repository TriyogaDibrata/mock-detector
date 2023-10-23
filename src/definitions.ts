import type { Plugin } from '@capacitor/core';

export interface MockDetectorPlugin extends Plugin {
  detectMock(): Promise<MockDetectorResult>;
}

export interface MockDetectorResult {
  isMock: boolean;
  message: string;
}
