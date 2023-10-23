import type { Plugin } from '@capacitor/core';

export interface MockDetectorPlugin extends Plugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  detectMock(): Promise<MockDetectorResult>;
}

export interface MockDetectorResult {
  isMock: boolean;
  message: string;
}
