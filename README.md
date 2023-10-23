# mock-detector

Fake Gps Detector Plugin Android Only

## Install

```bash
npm install mock-detector
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`detectMock()`](#detectmock)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### detectMock()

```typescript
detectMock() => Promise<MockDetectorResult>
```

**Returns:** <code>Promise&lt;<a href="#mockdetectorresult">MockDetectorResult</a>&gt;</code>

--------------------


### Interfaces


#### MockDetectorResult

| Prop          | Type                 |
| ------------- | -------------------- |
| **`isMock`**  | <code>boolean</code> |
| **`message`** | <code>string</code>  |

</docgen-api>
